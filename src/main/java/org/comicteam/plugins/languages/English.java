package org.comicteam.plugins.languages;

import org.comicteam.annotations.Language;

@Language
public class English extends org.comicteam.plugins.languages.Language {
    public English() {
        super("English","1.0","English language");
    }

    //MainController
    private final String newProjectButton = "New project";
    private final String openProjectButton = "Open project";
    private final String settingsButton = "Settings";

    //General Settings
    private final String languageLabel = "Language : ";
    private final String defaultSavePathLabel = "Default save path : ";

    //Document settings
    private final String nameLabel = "Name :";
    private final String serieLabel = "Serie : ";
    private final String authorsLabel = "Authors : ";
    private final String descriptionLabel = "Description : ";

    //New Project
    private final String createProjectButton = "Create project";

    //Working
    private final String menuButton = "Menu";
    private final String widthLabel = "Width : ";
    private final String heightLabel = "Height : ";

    //Menu
    private final String saveProjectButton = "Save project";
    private final String exportProjectButton = "Export to PDF";
    private final String pluginsButton = "Plugins";
    private final String printButton = "Print";
    private final String quitButton = "Quit";
    private final String updateButton = "New update !";

    //ModelEditor
    private final String eraserButton = "Eraser";
    private final String penButton = "Pen";
    private final String lineButton = "Line";
    private final String modelNameLabel = "Model name : ";

    //RightClickBook
    private final String addPageButton = "Add page";

    //RightClickPage
    private final String deletePageButton = "Delete page";
    private final String addPanelButton = "Add panel";
    private final String upButton = "Up";
    private final String downButton = "Down";

    //RightClickPanel
    private final String addClipartModelButton = "Add a model with Clipart";
    private final String addModelWithEditorButton = "Add a model from editor";
    private final String addModelWithExternalDocumentButton = "Add a model from external document";
    private final String deletePanelButton = "Delete panel";

    //RightClickModel
    private final String modifyModelButton = "Modify model";
    private final String deleteModelButton = "Delete model";

    //Clipart
    private final String searchButton = "Search";

    //SavingWarning
    private final String questionLabel = "Do you want to save the opened document ?";
    private final String yesButton = "Yes";
    private final String noButton = "No";
    private final String cancelButton = "Cancel";

    //Plugins
    private final String addPluginButton = "Add a plugin";
    private final String deletePluginButton = "Delete";

    @Override
    public String getTranslation(String name) throws NoSuchFieldException, IllegalAccessException {
        return (String) getClass().getDeclaredField(name).get(this);
    }

    @Override
    public void action() {

    }
}