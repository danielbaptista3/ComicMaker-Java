package org.comicteam.annotations;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes(value= {"org.comicteam.annotation.Maj"})
@SupportedSourceVersion(SourceVersion.RELEASE_9)
public class MajProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("Traitement Maj");

        File file = new File("/home/francois/Bureau/okokok");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
