package service;

import model.Label;
import repository.LabelRepository;
import repository.io.LabelRepositoryImpl;

import java.util.List;

public class LabelService {
    LabelRepository labelRepository = LabelRepositoryImpl.getInstance();

    public void setLabelRepository(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label save(Label type) {
        return labelRepository.save(type);
    }


    public Label update(Label type) {
        return labelRepository.update(type);
    }


    public Label getById(Long aLong) {
        return labelRepository.getById(aLong);
    }


    public void deleteById(Long aLong) {
        labelRepository.deleteById(aLong);
    }


    public List<Label> getAll() {
        return labelRepository.getAll();
    }
}
