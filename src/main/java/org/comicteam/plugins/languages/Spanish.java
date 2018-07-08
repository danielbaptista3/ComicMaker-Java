package org.comicteam.plugins.languages;

import org.comicteam.annotations.Language;

@Language
public class Spanish extends org.comicteam.plugins.languages.Language {
    public Spanish() {
        super("Spanish", "1.0", "Spanish language");
    }

    //MainController
    private final String newProjectButton = "Nuevo proyecto";
    private final String openProjectButton = "Abrir proyecto";
    private final String settingsButton = "Parámetros";

    //General Settings
    private final String languageLabel = "Idioma : ";
    private final String defaultSavePathLabel = "Ruta de grabación predeterminada : ";

    //Document settings
    private final String nameLabel = "Nombre :";
    private final String serieLabel = "Série : ";
    private final String authorsLabel = "Autores : ";
    private final String descriptionLabel = "Descripción : ";

    //New Project
    private final String createProjectButton = "Crear el proyecto";

    //Working
    private final String menuButton = "Menú";
    private final String widthLabel = "Largo : ";
    private final String heightLabel = "Ancho : ";

    //Menu
    private final String saveProjectButton = "Guardar proyecto";
    private final String exportProjectButton = "Exportar a PDF";
    private final String pluginsButton = "Plugins";
    private final String printButton = "Imprimir";
    private final String quitButton = "Saliendo";

    //ModelEditor
    private final String eraserButton = "Borrador";
    private final String penButton = "Lápiz";
    private final String lineButton = "Línea";
    private final String modelNameLabel = "Nombre del modelo : ";

    //RightClickBook
    private final String addPageButton = "Añadir una página";

    //RightClickPage
    private final String deletePageButton = "Eliminar página";
    private final String addPanelButton = "Añadir una caja";
    private final String upButton = "Alto";
    private final String downButton = "Descenso";

    //RightClickPanel
    private final String addClipartModelButton = "Ajouter un modèle avec Clipart";
    private final String addModelWithEditorButton = "Añadir una plantilla desde el editor";
    private final String addModelWithExternalDocumentButton = "Añadir una plantilla de un documento externo";
    private final String deletePanelButton = "Suprimir la caja";

    //RightClickModel
    private final String modifyModelButton = "Modificar el modelo";
    private final String deleteModelButton = "Eliminar la plantilla";

    //Clipart
    private final String searchButton = "Buscar";

    //SavingWarning
    private final String questionLabel = "¿Desea grabar el documento abierto?";
    private final String yesButton = "Sí";
    private final String noButton = "No";
    private final String cancelButton = "Cancelar";

    @Override
    public String getTranslation(String name) throws NoSuchFieldException, IllegalAccessException {
        return (String) getClass().getDeclaredField(name).get(this);
    }
}