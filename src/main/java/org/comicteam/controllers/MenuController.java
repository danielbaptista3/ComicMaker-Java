package org.comicteam.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.comicteam.CMFile;
import org.comicteam.PluginsForm;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.helpers.UpdateHelper;
import org.comicteam.layouts.ComicPage;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;

public class MenuController {
    public static MenuController controller;

    @Translate
    @FXML
    public Button newProjectButton;
    @Translate
    @FXML
    public Button openProjectButton;
    @Translate
    @FXML
    public Button saveProjectButton;
    @Translate
    @FXML
    public Button settingsButton;
    @Translate
    @FXML
    public Button pluginsButton;
    @Translate
    @FXML
    public Button printButton;
    @Translate
    @FXML
    public Button updateButton;
    @Translate
    @FXML
    public Button quitButton;

    public void initialize() {
        if (!UpdateHelper.newVersionIsAvailable()) {
            updateButton.setVisible(false);
        }

        TranslateProcessor.translate(MenuController.class, this);
        controller = this;
    }

    @FXML
    public void newProjectButtonClick() {
        if (!CMFile.cmfile.saved) {
            FXMLHelper.openSavingWarningForm();
        }

        FXMLHelper.openNewProjectForm();
    }

    @FXML
    public void openProjectButtonClick() {
        if (!CMFile.cmfile.saved) {
            FXMLHelper.openSavingWarningForm();
        }

        if (FXMLHelper.openProject(openProjectButton)) {
            FXMLHelper.closeAllWindows(openProjectButton);
            FXMLHelper.openWorkingForm();
        }
    }

    @FXML
    public void saveProjectButtonClick() {
        FXMLHelper.saveProject();

        int currentPage = CMFile.cmfile.currentPage;
        int p = 1;

        PDDocument doc = new PDDocument();

        for (ComicPage page : CMFile.cmfile.book.getPages()) {
            WorkingController.controller.selectPage(page);
            doc.addPage(writePage(doc));
            p++;
        }

        try {
            doc.save(new File("/home/francois/Bureau/okok.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        WorkingController.controller.selectPage(CMFile.cmfile.book.getPages().get(currentPage));
    }

    public PDPage writePage(PDDocument document) {
        WritableImage wi = new WritableImage(
                (int) EditorController.controller.editorPane.getWidth(),
                (int) EditorController.controller.editorPane.getHeight());

        wi = EditorController.controller.editorPane.snapshot(null, wi);
        BufferedImage bi = SwingFXUtils.fromFXImage(wi, null);

        /*FileImageOutputStream file = null;
        try {
            file = new FileImageOutputStream(new File("/home/francois/Bureau/page" + page + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        try {
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PDPage page = new PDPage();
        PDPageContentStream stream = null;
        try {
            stream = new PDPageContentStream(document, page);

            PDImageXObject x = PDImageXObject.createFromByteArray(document, bais.readAllBytes(), null);
            stream.drawImage(x, 0, 0);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return page;
    }

    @FXML
    public void settingsButtonClick() {
        FXMLHelper.openSettingsForm();
    }

    @FXML
    public void pluginsButtonClick() {
        PluginsForm pf = new PluginsForm();
        pf.start(new Stage(StageStyle.DECORATED));
    }

    @FXML
    public void printButtonClick() {
        /*PrinterJob job = javafx.print.PrinterJob.createPrinterJob();
        job.showPrintDialog(printButton.getScene().getWindow());*/
    }

    @FXML
    public void quitButtonClick() {
        if (!CMFile.cmfile.saved) {
            FXMLHelper.openSavingWarningForm();
        } else {
            FXMLHelper.closeAllWindows(quitButton);
        }
    }

    @FXML
    public void updateButtonClick() {
        UpdateHelper.writeLatestVersionFile();
    }
}