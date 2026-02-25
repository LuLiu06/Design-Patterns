package fi.metropolia.assignment7.state;

import fi.metropolia.assignment7.character.GameCharacter;

import java.util.Random;

/**
 * Expert state - the character can train, meditate, and fight at this level.
 */
public class ExpertState implements CharacterState {
    
    private static final int TRAIN_XP_GAIN = 35;
    private static final int MEDITATE_HP_GAIN = 25;
    private static final int FIGHT_XP_GAIN_MIN = 40;
    private static final int FIGHT_XP_GAIN_MAX = 80;
    private static final int FIGHT_HP_LOSS_MIN = 10;
    private static final int FIGHT_HP_LOSS_MAX = 30;
    
    private final Random random = new Random();
    
    @Override
    public void train(GameCharacter character) {
        System.out.println("\n⚔ Training with expert precision...");
        character.addExperience(TRAIN_XP_GAIN);
    }
    
    @Override
    public void meditate(GameCharacter character) {
        System.out.println("\n🧘 Deep meditation to heal wounds...");
        character.addHealth(MEDITATE_HP_GAIN);
    }
    
    @Override
    public void fight(GameCharacter character) {
        System.out.println("\n⚔ Engaging in battle!");
        
        int xpGain = random.nextInt(FIGHT_XP_GAIN_MAX - FIGHT_XP_GAIN_MIN + 1) + FIGHT_XP_GAIN_MIN;
        int hpLoss = random.nextInt(FIGHT_HP_LOSS_MAX - FIGHT_HP_LOSS_MIN + 1) + FIGHT_HP_LOSS_MIN;
        
        // Critical hit chance
        if (random.nextDouble() < 0.2) {
            System.out.println("  ★ CRITICAL HIT! Double experience!");
            xpGain *= 2;
        }
        
        // Dodge chance
        if (random.nextDouble() < 0.15) {
            System.out.println("  ★ PERFECT DODGE! No damage taken!");
            hpLoss = 0;
        }
        
        character.addExperience(xpGain);
        if (hpLoss > 0) {
            character.reduceHealth(hpLoss);
        }
    }
    
    @Override
    public String getLevelName() {
        return "Expert";
    }
    
    @Override
    public String[] getAvailableActions() {
        return new String[]{"Train (+35 XP)", "Meditate (+25 HP)", "Fight (+40~80 XP, -10~30 HP)"};
    }
    
    @Override
    public void checkLevelUp(GameCharacter character) {
        if (character.getExperiencePoints() >= GameCharacter.MASTER_THRESHOLD) {
            character.setState(new MasterState());
        }
    }
}

