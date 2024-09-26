package com.pj.service;

import com.pj.model.Appuser;
import com.pj.repo.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles business logic to get, create, update Books
 *
 * @author Pavan Kumar Jadda
 * @since 2.0.0
 */
@Service
@Transactional
public class AppuserServiceImpl implements AppuserService {
    private final AppUserRepository appuserRepository;

    public AppuserServiceImpl(AppUserRepository appuserRepository) {
        this.appuserRepository = appuserRepository;
    }

    /**
     * Find all books
     *
     * @return List of all books
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @Override
    public List<Appuser> findAll() {
        return appuserRepository.findAll();
    }

    /**
     * Find the book by ID
     *
     * @param id ID of the book that needs to be found
     *
     * @return Book matching the given ID
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @Override
    public Optional<Appuser> findById(String id) {
        return appuserRepository.findById(id);
    }

    /**
     * Save the new book
     *
     * @param book an object that contains new book information
     *
     * @return Created book
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
 
    public Appuser insertAppuser(Appuser appuser) {
        System.out.println(appuser);
        return appuserRepository.insert(appuser);
    }

    @Override
    public void insertAllAppuser(Iterable<Appuser> appuser) {
        appuserRepository.insert(appuser);
    }

    /**
     * Update the book
     *
     * @param book an object that contains updated book information
     *
     * @return Updated book
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @Override
    public Appuser updateAppuser(Appuser appuser) {
        return appuserRepository.save(appuser);
    }

    /**
     * Delete book by ID
     *
     * @param id ID of the book that needs to be deleted
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @Override
    public void deleteAppuser(Appuser appuser) {
        appuserRepository.delete(appuser);
    }

    @Override
    public void deleteById(String id) {
        appuserRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        appuserRepository.deleteAll();
    }
}
