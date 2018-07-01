package org.comicteam.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("org.comicteam.annotations.Translate")
@SupportedSourceVersion(SourceVersion.RELEASE_9)
public class TranslateProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("Début du traitement Translate");

        for (TypeElement o : annotations) {
            System.out.println(o.getSimpleName());
        }

        return true;
    }
}