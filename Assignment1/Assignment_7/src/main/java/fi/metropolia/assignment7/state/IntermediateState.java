package fi.metropolia.assignment7.state;

import fi.metropolia.assignment7.character.GameCharacter;

/**
 * Intermediate state - the character can train and meditate at this level.
 */
public class IntermediateState implements CharacterState {
    
    private static final int TRAIN_XP_GAIN = 30;
    private static final int MEDITATE_HP_GAIN = 20;
    
    @Override
    public void train(GameCharacter character) {
        System.out.println("\n⚔ Training with improved technique...");
        character.addExperience(TRAIN_XP_GAIN);
    }
    
    @Override
    public void meditate(GameCharacter character) {
        System.out.println("\n🧘 Meditating to restore inner peace...");
        character.addHealth(MEDITATE_HP_GAIN);
    }
    
    @Override
    public void fight(GameCharacter character) {
        System.out.println("\n✗ Intermediate characters cannot fight yet. More training needed!");
    }
    
    @Override
    public String getLevelName() {
        return "Intermediate";
    }
    
    @Override
    public String[] getAvailableActions() {
        return new String[]{"Train (+30 XP)", "Meditate (+20 HP)"};
    }
    
    @Override
    public void checkLevelUp(GameCharacter character) {
        if (character.getExperiencePoints() >= GameCharacter.EXPERT_THRESHOLD) {
            character.setState(new ExpertState());
        }
    }
}

