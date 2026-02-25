package fi.metropolia.assignment7.state;

import fi.metropolia.assignment7.character.GameCharacter;

/**
 * Master state - the final level. The game ends when this level is reached.
 */
public class MasterState implements CharacterState {
    
    @Override
    public void train(GameCharacter character) {
        System.out.println("\n✓ You have mastered all there is to learn!");
    }
    
    @Override
    public void meditate(GameCharacter character) {
        System.out.println("\n✓ Your mind has achieved perfect harmony!");
    }
    
    @Override
    public void fight(GameCharacter character) {
        System.out.println("\n✓ No opponent can challenge a true master!");
    }
    
    @Override
    public String getLevelName() {
        return "Master";
    }
    
    @Override
    public String[] getAvailableActions() {
        return new String[]{"You have completed your journey!"};
    }
    
    @Override
    public void checkLevelUp(GameCharacter character) {
        // Master is the final level, no more level ups
    }
}

