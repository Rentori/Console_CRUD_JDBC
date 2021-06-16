package controller;

import model.Label;
import service.LabelService;

import java.util.List;

public class LabelController {
    LabelService labelService = new LabelService();

    public Label save(Label type) {
        return labelService.save(type);
    }

    public Label update(Label type) {
        return labelService.update(type);
    }

    public Label getById(Long aLong) {
        return labelService.getById(aLong);
    }

    public void deleteById(Long aLong) {
        labelService.deleteById(aLong);
    }

    public List<Label> getAll() {
        return labelService.getAll();
    }
}
