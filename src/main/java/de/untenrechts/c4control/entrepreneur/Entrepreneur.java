package de.untenrechts.c4control.entrepreneur;

public class Entrepreneur implements IEntrepreneur {

    // TODO: 4/4/20 default this to false
    private boolean entrepreneur = true;
    private Float accountBalance;

    /**
     * Factor by which to determine the monetary value of each charged second.
     * E.g.:
     * - Charging for one second will result in a monetary value of v = 1 * chargeMultiplier
     * - Charging for five seconds will result in a monetary value of v = 5 * chargeMultiplier
     */
    private Float chargeMultiplier;
    private Float activeChargeValue;
    private Long lastChargeStart;

    @Override
    public boolean isEntrepreneur() {
        return entrepreneur;
    }

    @Override
    public void setEntrepreneur(boolean entrepreneur) {
        this.entrepreneur = entrepreneur;
        if (this.entrepreneur) {
            accountBalance = DEFAULT_ACCOUNT_BALANCE;
            chargeMultiplier = DEFAULT_CHARGE_MULTIPLIER;
            activeChargeValue = DEFAULT_ACTIVE_CHARGE_VALUE;
        } else {
            accountBalance = null;
            chargeMultiplier = null;
            activeChargeValue = null;
        }
    }

    @Override
    public float getAccountBalance() {
        return accountBalance;
    }

    @Override
    public void setAccountBalance(float balance) {
        this.accountBalance = balance;
    }

    @Override
    public float getChargeMultiplier() {
        return chargeMultiplier;
    }

    @Override
    public void setChargeMultiplier(float chargeMultiplier) {
        this.chargeMultiplier = chargeMultiplier;
    }

    @Override
    public float getActiveChargeValue() {
        return activeChargeValue;
    }

    @Override
    public void setActiveChargeValue(float activeChargeValue) {
        this.activeChargeValue = activeChargeValue;
    }

    @Override
    public void startCharge() {
        lastChargeStart = System.nanoTime();
    }

    @Override
    public void stopCharge() {
        long chargeDiff = System.nanoTime() - lastChargeStart;
        lastChargeStart = null;

        float chargedSeconds = chargeDiff / 1_000_000_000f;
        float addedChargeValue = chargedSeconds * chargeMultiplier;
        float attemptedChargeValue = activeChargeValue + addedChargeValue;
        activeChargeValue = Math.min(attemptedChargeValue, accountBalance);
    }

    @Override
    public void startDischarge() {

    }

    @Override
    public void stopDischarge() {

    }
}
