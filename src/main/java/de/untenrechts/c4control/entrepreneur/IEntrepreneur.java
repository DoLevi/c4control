package de.untenrechts.c4control.entrepreneur;

import net.minecraft.entity.Entity;

import javax.annotation.Nullable;
import java.util.Optional;

public interface IEntrepreneur {
    /**
     * These damage calculation constants will (approximately) result in:
     * * 2 damage for a charge of 500
     * * 40 damage for a charge of 300_000
     */
    double DMG_CALC_A_CONST = Math.pow(300_000 / Math.pow(500, 20), 1 / 19f);
    double DMG_CALC_B_CONST = 1 / Math.log(Math.sqrt(500 * DMG_CALC_A_CONST));
    /**
     * This value prevents the result fo the logarithmic damage calculation from dropping below zero
     */
    float MIN_CHARGE_VALUE = (float) Math.pow(300_000 / Math.pow(500, 20), -1 / 19f);

    float DEFAULT_ACCOUNT_BALANCE = 10_000f;
    float DEFAULT_CHARGE_MULTIPLIER = 100f;
    float DEFAULT_ACTIVE_CHARGE_VALUE = 0;

    boolean isEntrepreneur();

    void setEntrepreneur(boolean entrepreneur);

    float getAccountBalance();

    void setAccountBalance(final float balance);

    float getChargeMultiplier();

    void setChargeMultiplier(float chargeMultiplier);

    float getActiveChargeValue();

    void setActiveChargeValue(float activeChargeValue);

    void startCharge();

    void stopCharge();

    void setDischargeEnabled(boolean dischargeEnabled);

    default float getExpectedDamage() {
        return (float) (DMG_CALC_B_CONST * Math.log(DMG_CALC_A_CONST * getActiveChargeValue()));
    }

    /**
     * This method is used to ensure the IEntrepreneur object has a consistent state.
     * This would be relevant if the C4Control Mod is added after Entities have been loaded.
     */
    default void ensureConsistentState() {
        try {
            if (isEntrepreneur()) {
                getAccountBalance();
            }
        } catch (NullPointerException e) {
            setAccountBalance(DEFAULT_ACCOUNT_BALANCE);
            setChargeMultiplier(DEFAULT_CHARGE_MULTIPLIER);
            setActiveChargeValue(DEFAULT_ACTIVE_CHARGE_VALUE);
        }
    }

    /**
     * Use this method to get the (active!) IEntrepreneur capability of an Entity.
     *
     * @param entity Entity for which to retrieve the Entrepreneur capability
     * @return Optional<IEntrepreneur> for an active IEntrepreneur and Optional.empty() for any other status
     */
    static Optional<IEntrepreneur> getActiveEntrepreneur(@Nullable Entity entity) {
        return getEntrepreneur(entity, true);
    }

    /**
     * Use this method to get the IEntrepreneur capability of an Entity.
     *
     * @param entity Entity for which to retrieve the Entrepreneur capability
     * @return Optional<IEntrepreneur> for any IEntrepreneur and Optional.empty() for any other status
     */
    static Optional<IEntrepreneur> getAnyEntrepreneur(Entity entity) {
        if (entity != null) {
            IEntrepreneur entrepreneur = entity.getCapability(EntrepreneurProvider.entrepreneur, null);
            if (entrepreneur != null) {
                return Optional.of(entrepreneur);
            }
        }
        return Optional.empty();
    }

    static Optional<IEntrepreneur> getEntrepreneur(@Nullable Entity entity, boolean strictCheck) {
        if (entity != null) {
            IEntrepreneur entrepreneur = entity.getCapability(EntrepreneurProvider.entrepreneur, null);
            // capabilityPresent AND activenessIfStrictCheck
            if (entrepreneur != null && (!strictCheck || entrepreneur.isEntrepreneur())) {
                return Optional.of(entrepreneur);
            }
        }
        return Optional.empty();
    }
}
