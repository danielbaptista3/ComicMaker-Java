package org.comicteam.languages;

import org.comicteam.annotations.Language;
import org.comicteam.annotations.LanguageProcessor;

@Language
public class English extends org.comicteam.languages.Language {
    static {
        LanguageProcessor.language(English.class);
    }

    //MainController
    public static final String newProjectButton = "New project";
    public static final String openProjectButton = "Open project";
    public static final String settingsButton = "Settings";

    //General Settings
    public static final String languageLabel = "Language : ";
    public static final String defaultSavePathLabel = "Default save path : ";

    //Document settings
    public static final String nameLabel = "Name :";
    public static final String serieLabel = "Serie : ";
    public static final String authorsLabel = "Authors : ";
    public static final String descriptionLabel = "Description : ";

    //New Project
    public static final String createProjectButton = "Create project";

    //Working
    public static final String menuButton = "Menu";
    public static final String widthLabel = "Width : ";
    public static final String heightLabel = "Height : ";

    //Menu
    public static final String saveProjectButton = "Save project";
    public static final String exportProjectButton = "Export to PDF";
    public static final String pluginsButton = "Plugins";
    public static final String printButton = "Print";
    public static final String quitButton = "Quit";

    //ModelEditor
    public static final String eraserButton = "Eraser";
    public static final String penButton = "Pen";
    public static final String lineButton = "Line";
    public static final String modelNameLabel = "Model name : ";

    //RightClickBook
    public static final String addPageButton = "Add page";

    //RightClickPage
    public static final String deletePageButton = "Delete page";
    public static final String addPanelButton = "Add panel";
    public static final String upButton = "Up";
    public static final String downButton = "Down";

    //RightClickPanel
    public static final String addClipartModelButton = "Add a model with Clipart";
    public static final String addModelWithEditorButton = "Add a model from editor";
    public static final String addModelWithExternalDocumentButton = "Add a model from external document";
    public static final String deletePanelButton = "Delete panel";

    //RightClickModel
    public static final String modifyModelButton = "Modify model";
    public static final String deleteModelButton = "Delete model";

    //Clipart
    public static final String searchButton = "Search";
}
