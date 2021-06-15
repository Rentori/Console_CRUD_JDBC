package service;

import model.Writer;
import repository.WriterRepository;
import repository.io.WriterRepositoryImpl;

import java.util.List;

public class WriterService {
    WriterRepository writerRepository = WriterRepositoryImpl.getInstance();

    public void setWriterRepository(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer save(Writer type) {
        return writerRepository.save(type);
    }

    public Writer update(Writer type) {
        return writerRepository.update(type);
    }

    public Writer getById(Long aLong) {
        return writerRepository.getById(aLong);
    }

    public void deleteById(Long aLong) {
        writerRepository.deleteById(aLong);
    }

    public List<Writer> getAll() {
        return writerRepository.getAll();
    }
}
