package fi.metropolia.assignment7.state;

import fi.metropolia.assignment7.character.GameCharacter;

/**
 * State interface for the State design pattern.
 * Defines the actions available for a game character at different levels.
 */
public interface CharacterState {
    
    /**
     * Train action - increases experience points.
     * @param character the game character
     */
    void train(GameCharacter character);
    
    /**
     * Meditate action - increases health points.
     * @param character the game character
     */
    void meditate(GameCharacter character);
    
    /**
     * Fight action - decreases health but increases experience.
     * @param character the game character
     */
    void fight(GameCharacter character);
    
    /**
     * Gets the name of the current level/state.
     * @return the level name
     */
    String getLevelName();
    
    /**
     * Gets the available actions at this level.
     * @return array of available action names
     */
    String[] getAvailableActions();
    
    /**
     * Checks if the character should advance to the next level.
     * @param character the game character
     */
    void checkLevelUp(GameCharacter character);
}

