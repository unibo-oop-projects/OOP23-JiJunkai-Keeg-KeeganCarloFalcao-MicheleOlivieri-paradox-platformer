/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package com.project.paradoxplatformer.model.endgame;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 *
 * @author michele
 */
public class DeathConditionsFactoryImplTest {

    public DeathConditionsFactoryImplTest() {
    }

    /**
     * Test of createConditionsForLevel method, of class DeathConditionsFactoryImpl.
     */
    @Test
    public void testCreateConditionsForLevel() {
        System.out.println("createConditionsForLevel");
        Level level = null;
        PlayerModel player = null;
        DeathConditionsFactoryImpl instance = new DeathConditionsFactoryImpl();
        Iterator<DeathCondition> expResult = null;
        Iterator<DeathCondition> result = instance.createConditionsForLevel(level, player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of defaultConditions method, of class DeathConditionsFactoryImpl.
     */
    @Test
    public void testDefaultConditions() {
        System.out.println("defaultConditions");
        DeathConditionsFactoryImpl instance = new DeathConditionsFactoryImpl();
        Iterator<DeathCondition> expResult = null;
        Iterator<DeathCondition> result = instance.defaultConditions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of levelOneConditions method, of class DeathConditionsFactoryImpl.
     */
    @Test
    public void testLevelOneConditions() {
        System.out.println("levelOneConditions");
        DeathConditionsFactoryImpl instance = new DeathConditionsFactoryImpl();
        Iterator<DeathCondition> expResult = null;
        Iterator<DeathCondition> result = instance.levelOneConditions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of levelTwoConditions method, of class DeathConditionsFactoryImpl.
     */
    @Test
    public void testLevelTwoConditions() {
        System.out.println("levelTwoConditions");
        DeathConditionsFactoryImpl instance = new DeathConditionsFactoryImpl();
        Iterator<DeathCondition> expResult = null;
        Iterator<DeathCondition> result = instance.levelTwoConditions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of levelThreeConditions method, of class DeathConditionsFactoryImpl.
     */
    @Test
    public void testLevelThreeConditions() {
        System.out.println("levelThreeConditions");
        DeathConditionsFactoryImpl instance = new DeathConditionsFactoryImpl();
        Iterator<DeathCondition> expResult = null;
        Iterator<DeathCondition> result = instance.levelThreeConditions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of levelFourConditions method, of class DeathConditionsFactoryImpl.
     */
    @Test
    public void testLevelFourConditions() {
        System.out.println("levelFourConditions");
        DeathConditionsFactoryImpl instance = new DeathConditionsFactoryImpl();
        Iterator<DeathCondition> expResult = null;
        Iterator<DeathCondition> result = instance.levelFourConditions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}