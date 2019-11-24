package inc.primssware.mylibrary.iconify;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class Module implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "fonts/icons.ttf";
    }

    @Override
    public Icon[] characters() {
        return Icons.values();
    }

}
