package fi.metropolia.assignment7.state;

import fi.metropolia.assignment7.character.GameCharacter;

/**
 * Novice state - the character can only train at this level.
 */
public class NoviceState implements CharacterState {
    
    private static final int TRAIN_XP_GAIN = 25;
    
    @Override
    public void train(GameCharacter character) {
        System.out.println("\n⚔ Training hard as a novice...");
        character.addExperience(TRAIN_XP_GAIN);
    }
    
    @Override
    public void meditate(GameCharacter character) {
        System.out.println("\n✗ Novices cannot meditate yet. Keep training!");
    }
    
    @Override
    public void fight(GameCharacter character) {
        System.out.println("\n✗ Novices cannot fight yet. Keep training!");
    }
    
    @Override
    public String getLevelName() {
        return "Novice";
    }
    
    @Override
    public String[] getAvailableActions() {
        return new String[]{"Train (+25 XP)"};
    }
    
    @Override
    public void checkLevelUp(GameCharacter character) {
        if (character.getExperiencePoints() >= GameCharacter.INTERMEDIATE_THRESHOLD) {
            character.setState(new IntermediateState());
        }
    }
}

