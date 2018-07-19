package org.comicteam.plugins;

import javafx.embed.swing.SwingFXUtils;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.comicteam.CMFile;
import org.comicteam.controllers.EditorController;
import org.comicteam.controllers.MenuController;
import org.comicteam.controllers.WorkingController;
import org.comicteam.helpers.SettingsHelper;
import org.comicteam.layouts.ComicPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PDFExport extends Plugin {

    public PDFExport() {
        super("PDF Export", "1.0", "This plugin is to convert a comic to pdf");
    }

    @Override
    public void action() {
        Button pdf = new Button("Export pdf");
        pdf.setPrefHeight(50);

        Button print = new Button("Print");
        print.setPrefHeight(50);

        if (MenuController.controller != null) {
            MenuController.controller.pluginsBox.getChildren().add(pdf);
            MenuController.controller.pluginsBox.getChildren().add(print);
        }

        pdf.setOnAction(e -> {
            writePDDocument();
        });

        print.setOnAction(e -> {
            printPDDocument(print);
        });
    }

    public PDPage writePage(PDDocument document) {
        WritableImage wi = new WritableImage(
                (int) EditorController.controller.editorPane.getWidth(),
                (int) EditorController.controller.editorPane.getHeight());

        wi = EditorController.controller.editorPane.snapshot(null, wi);
        BufferedImage bi = SwingFXUtils.fromFXImage(wi, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PDPage page = new PDPage();
        PDPageContentStream stream = null;
        try {
            stream = new PDPageContentStream(document, page);

            PDImageXObject x = PDImageXObject.createFromByteArray(document, baos.toByteArray(), null);
            stream.drawImage(x, 0, 0, page.getArtBox().getWidth(), page.getArtBox().getHeight());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return page;
    }

    public PDDocument getPDDocument() {
        int currentPage = CMFile.cmfile.currentPage;

        PDDocument doc = new PDDocument();

        for (ComicPage page : CMFile.cmfile.book.getPages()) {
            WorkingController.controller.selectPage(page);
            doc.addPage(writePage(doc));
        }

        WorkingController.controller.selectPage(CMFile.cmfile.book.getPages().get(currentPage));

        return doc;
    }

    public void writePDDocument() {
        PDDocument doc = getPDDocument();

        try {
            doc.save(new File(String.format("%s/%s.pdf", SettingsHelper.get("savePath"), CMFile.cmfile.book.getName())));
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPDDocument(Button print) {
        int currentPage = CMFile.cmfile.currentPage;
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob job = PrinterJob.createPrinterJob(printer);

        if (job == null) {
            System.out.println("Printer not found");
            return;
        }

        /*for (ComicPage page : CMFile.cmfile.book.getPages()) {PageLayout
            WorkingController.controller.selectPage(page);

            job.showPrintDialog(print.getScene().getWindow())//.printPage(EditorController.controller.editorPane);
        }*/

        PageLayout pl = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 0, 0, 0, 0);

        job.printPage(pl, EditorController.controller.editorPane);

        //job.showPrintDialog(print.getScene().getWindow());

        job.endJob();

        WorkingController.controller.selectPage(CMFile.cmfile.book.getPages().get(currentPage));
    }
}