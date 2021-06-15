package controller;

import model.Label;
import repository.GenericRepository;
import service.LabelService;

import java.util.List;

public class LabelController implements GenericRepository<Label, Long> {
    LabelService labelService = new LabelService();

    @Override
    public Label save(Label type) {
        return labelService.save(type);
    }

    @Override
    public Label update(Label type) {
        return labelService.update(type);
    }

    @Override
    public Label getById(Long aLong) {
        return labelService.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        labelService.deleteById(aLong);
    }

    @Override
    public List<Label> getAll() {
        return labelService.getAll();
    }
}
