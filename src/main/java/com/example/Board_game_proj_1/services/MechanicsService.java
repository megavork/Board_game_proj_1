package com.example.Board_game_proj_1.services;

import com.example.Board_game_proj_1.dao.MechanicsDao;
import com.example.Board_game_proj_1.entity.Mechanic;
import lombok.NoArgsConstructor;

import java.util.List;

/*
прослойка между ДАО и СУБД. нужен, чтобы управлять работой ДАО. прослойка между ДАО и СУБД
 */
@NoArgsConstructor
public class MechanicsService {
    private MechanicsDao mechanicsDao = new MechanicsDao();

    /**
     * Return one Mechanic from base
     * @param idMechanic
     * @return
     */
    public Mechanic findCategoryById(String idMechanic) {
        return mechanicsDao.findById(idMechanic);
    }

    /**
     * Save Object in Mechanic base
     * @param category
     */
    public void saveCategory(Mechanic category) {
        mechanicsDao.save(category);
    }

    /**
     * Update one Mechanic in base
     * @param category
     */
    public void updateCategory(Mechanic category) {
        mechanicsDao.update(category);
    }

    /**
     * Delete one Mechanic from base
     * @param category
     */
    public void deleteCategory(Mechanic category) {
        mechanicsDao.delete(category);
    }

    /**
     * Return all Mechanics from base
     * @return
     */
    public List<Mechanic> findAll() {
        return mechanicsDao.findAll();
    }

    /**
     * Method will upload data from API and put it in base.
     * Won't work if base already has all data.
     * @return
     */
    public boolean uploadFromAPI() {
        return mechanicsDao.uploadFromAPI();
    }
}
