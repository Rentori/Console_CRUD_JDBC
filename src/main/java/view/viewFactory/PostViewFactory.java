package view.viewFactory;

import view.PostView;
import view.View;

public class PostViewFactory implements ViewFactory {
    @Override
    public View getView() {
        return new PostView();
    }
}
