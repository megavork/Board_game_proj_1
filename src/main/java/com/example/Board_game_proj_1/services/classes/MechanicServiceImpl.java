package com.example.Board_game_proj_1.services.classes;

import com.example.Board_game_proj_1.dao.interfaces.MechanicDao;
import com.example.Board_game_proj_1.entity.Mechanic;
import com.example.Board_game_proj_1.services.interfaces.MechanicService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@NoArgsConstructor
public class MechanicServiceImpl implements MechanicService {

    @Autowired
    MechanicDao mechanicDao;

    /**
     * Return one Mechanic from base
     * @param idMechanic
     * @return
     */
    @Override
    @Transactional
    public Mechanic findMechanicById(String idMechanic) {
        return mechanicDao.findById(idMechanic);
    }

    /**
     * Save Object in Mechanic base
     * @param category
     */
    @Override
    @Transactional
    public void saveMechanic(Mechanic category) {
        mechanicDao.save(category);
    }

    /**
     * Update one Mechanic in base
     * @param category
     */
    @Override
    @Transactional
    public void updateMechanic(Mechanic category) {
        mechanicDao.update(category);
    }

    /**
     * Delete one Mechanic from base
     * @param category
     */
    @Override
    @Transactional
    public void deleteMechanic(Mechanic category) {
        mechanicDao.delete(category);
    }

    /**
     * Return all Mechanics from base
     * @return
     */
    @Override
    @Transactional
    public List<Mechanic> findAll() {
        return mechanicDao.findAll();
    }

    /**
     * Method will upload data from API and put it in base.
     * Won't work if base already has all data.
     * @return
     */
    @Override
    @Transactional
    public boolean uploadFromAPI() {
        return mechanicDao.uploadFromAPI();
    }
}
