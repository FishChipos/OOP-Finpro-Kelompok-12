package com.sundaempire.frontend.settlement;

import com.sundaempire.frontend.faction.Faction;

public class CaptureService {

    public boolean tryCapture(Settlement settlement, Faction attacker, int attackPower) {
        return settlement.capture(attacker, attackPower);
    }
}
