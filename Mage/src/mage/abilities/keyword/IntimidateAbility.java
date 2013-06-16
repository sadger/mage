package mage.abilities.keyword;

import mage.abilities.Ability;
import mage.abilities.EvasionAbility;
import mage.abilities.MageSingleton;
import mage.abilities.effects.RestrictionEffect;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.game.Game;
import mage.game.permanent.Permanent;

public class IntimidateAbility extends EvasionAbility<IntimidateAbility> implements MageSingleton  {
    private static final IntimidateAbility fInstance = new IntimidateAbility();

    public static IntimidateAbility getInstance() {
        return fInstance;
    }

    private IntimidateAbility() {
        this.addEffect(new IntimidateEffect());
    }

    @Override
    public String getRule() {
        return "Intimidate";
    }

    @Override
    public IntimidateAbility copy() {
        return fInstance;
    }
}

class IntimidateEffect extends RestrictionEffect<IntimidateEffect> implements MageSingleton {
    public IntimidateEffect() {
        super(Duration.WhileOnBattlefield);
    }

    public IntimidateEffect(final IntimidateEffect effect) {
        super(effect);
    }

    @Override
    public boolean applies(Permanent permanent, Ability source, Game game) {
        if (permanent.getAbilities().containsKey(IntimidateAbility.getInstance().getId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canBeBlocked(Permanent attacker, Permanent blocker, Ability source, Game game) {
        boolean result = false;
        if (blocker.getCardType().contains(CardType.ARTIFACT) && (blocker.getCardType().contains(CardType.CREATURE)))
            result = true;
        if (attacker.getColor().shares(blocker.getColor()))
            result = true;
        return result;
    }

    @Override
    public IntimidateEffect copy() {
        return new IntimidateEffect(this);
    }
}