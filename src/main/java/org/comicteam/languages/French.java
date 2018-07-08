package org.comicteam.languages;

import org.comicteam.annotations.Language;
import org.comicteam.annotations.LanguageProcessor;

@Language
public class French extends org.comicteam.languages.Language {
    static {
        LanguageProcessor.language(French.class);
    }

    //MainController
    public static final String newProjectButton = "Nouveau projet";
    public static final String openProjectButton = "Ouvrir projet";
    public static final String settingsButton = "Paramètres";

    //General Settings
    public static final String languageLabel = "Langue : ";
    public static final String defaultSavePathLabel = "Chemin d'enregistrement par défaut : ";

    //Document settings
    public static final String nameLabel = "Nom :";
    public static final String serieLabel = "Série : ";
    public static final String authorsLabel = "Auteurs : ";
    public static final String descriptionLabel = "Description : ";

    //New Project
    public static final String createProjectButton = "Créer le projet";

    //Working
    public static final String menuButton = "Menu";
    public static final String widthLabel = "Longueur : ";
    public static final String heightLabel = "Largeur : ";

    //Menu
    public static final String saveProjectButton = "Sauvegarder projet";
    public static final String exportProjectButton = "Exporter en PDF";
    public static final String pluginsButton = "Plugins";
    public static final String printButton = "Imprimer";
    public static final String quitButton = "Quitter";

    //ModelEditor
    public static final String eraserButton = "Gomme";
    public static final String penButton = "Crayon";
    public static final String lineButton = "Ligne";
    public static final String modelNameLabel = "Nom du modèle : ";

    //RightClickBook
    public static final String addPageButton = "Ajouter une page";

    //RightClickPage
    public static final String deletePageButton = "Supprimer la page";
    public static final String addPanelButton = "Ajouter une case";
    public static final String upButton = "Monter";
    public static final String downButton = "Descendre";

    //RightClickPanel
    public static final String addClipartModelButton = "Ajouter un modèle avec Clipart";
    public static final String addModelWithEditorButton = "Ajouter un model à partir de l'éditeur";
    public static final String addModelWithExternalDocumentButton = "Ajouter un modèle à partir d'un document externe";
    public static final String deletePanelButton = "Supprimer la case";

    //RightClickModel
    public static final String modifyModelButton = "Modifier le modèle";
    public static final String deleteModelButton = "Supprimer le modèle";

    //Clipart
    public static final String searchButton = "Rechercher";
}
