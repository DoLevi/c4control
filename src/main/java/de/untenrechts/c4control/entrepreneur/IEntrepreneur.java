package de.untenrechts.c4control.entrepreneur;

public interface IEntrepreneur {
    /**
     * These damage calculation constants will (approximately) result in:
     * * 2 damage for a charge of 500
     * * 40 damage for a charge of 300_000
     */
    double DMG_CALC_A_CONST = Math.pow(300_000 / Math.pow(500, 20), 1 / 19f);
    double DMG_CALC_B_CONST = 1 / Math.log(Math.sqrt(500 * DMG_CALC_A_CONST));

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
}
