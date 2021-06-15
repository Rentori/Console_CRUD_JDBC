package view.viewFactory;

import view.View;
import view.WriterView;

public class WriterViewFactory implements ViewFactory {
    @Override
    public View getView() {
        return new WriterView();
    }
}
