package view.viewFactory;

import view.LabelView;
import view.View;

public class LabelViewFactory implements ViewFactory {
    @Override
    public View getView() {
        return new LabelView();
    }
}
