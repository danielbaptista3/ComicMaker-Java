package org.comicteam.plugins.languages;

import org.comicteam.annotations.Language;

@Language
public class French extends org.comicteam.plugins.languages.Language {
    public French() {
        super("French", "1.0", "French language");
    }

    //MainController
    private final String newProjectButton = "Nouveau projet";
    private final String openProjectButton = "Ouvrir projet";
    private final String settingsButton = "Paramètres";

    //General Settings
    private final String languageLabel = "Langue : ";
    private final String defaultSavePathLabel = "Chemin d'enregistrement par défaut : ";

    //Document settings
    private final String nameLabel = "Nom :";
    private final String serieLabel = "Série : ";
    private final String authorsLabel = "Auteurs : ";
    private final String descriptionLabel = "Description : ";

    //New Project
    private final String createProjectButton = "Créer le projet";

    //Working
    private final String menuButton = "Menu";
    private final String widthLabel = "Longueur : ";
    private final String heightLabel = "Largeur : ";

    //Menu
    private final String saveProjectButton = "Sauvegarder projet";
    private final String exportProjectButton = "Exporter en PDF";
    private final String pluginsButton = "Plugins";
    private final String printButton = "Imprimer";
    private final String quitButton = "Quitter";
    private final String updateButton = "Nouvelle mise à jour !";

    //ModelEditor
    private final String eraserButton = "Gomme";
    private final String penButton = "Crayon";
    private final String lineButton = "Ligne";
    private final String balloonButton = "Bulle";
    private final String modelNameLabel = "Nom du modèle : ";

    //RightClickBook
    private final String addPageButton = "Ajouter une page";

    //RightClickPage
    private final String deletePageButton = "Supprimer la page";
    private final String addPanelButton = "Ajouter une case";
    private final String upButton = "Monter";
    private final String downButton = "Descendre";

    //RightClickPanel
    private final String addClipartModelButton = "Ajouter un modèle avec Clipart";
    private final String addModelWithEditorButton = "Ajouter un modèle à partir de l'éditeur";
    private final String addModelWithExternalDocumentButton = "Ajouter un modèle à partir d'un document externe";
    private final String deletePanelButton = "Supprimer la case";

    //RightClickModel
    private final String modifyModelButton = "Modifier le modèle";
    private final String deleteModelButton = "Supprimer le modèle";

    //Clipart
    private final String searchButton = "Rechercher";

    //SavingWarning
    private final String questionLabel = "Voulez-vous enregistrer le document ouvert ?";
    private final String yesButton = "Oui";
    private final String noButton = "Non";
    private final String cancelButton = "Annuler";

    //Plugins
    private final String addPluginButton = "Ajouter un plugin";
    private final String deletePluginButton = "Supprimer";

    @Override
    public String getTranslation(String name) throws NoSuchFieldException, IllegalAccessException {
        return (String) getClass().getDeclaredField(name).get(this);
    }

    @Override
    public void action() {

    }
}