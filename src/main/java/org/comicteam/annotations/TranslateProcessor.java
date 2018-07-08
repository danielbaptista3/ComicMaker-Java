package org.comicteam.annotations;

import javafx.scene.control.Labeled;
import org.comicteam.helpers.LanguageHelper;

import java.lang.reflect.Field;

public class TranslateProcessor {

    public static void translate(Class c, Object o) {
        for (Field f : c.getFields()) {
            if (f.isAnnotationPresent(Translate.class)) {
                try {
                    Labeled n = (Labeled) f.get(o);
                    n.setText(LanguageHelper.getTranslation(f.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}