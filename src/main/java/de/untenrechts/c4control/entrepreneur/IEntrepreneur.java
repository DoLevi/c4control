package de.untenrechts.c4control.entrepreneur;

public interface IEntrepreneur {
    float DAMAGE_PER_VALUE_UNIT = 0.004F;
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

    void startDischarge();
    void stopDischarge();

    default float getExpectedDamage() {
        return getActiveChargeValue() * DAMAGE_PER_VALUE_UNIT;
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
}
