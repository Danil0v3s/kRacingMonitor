/*
 *
 *    Copyright (C) 2020 Joffrey Bonifay
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package iracing.yaml;

import kotlinx.serialization.Serializable;

import java.util.List;


@Serializable
public class DriversInfoYaml {


    public String DriverCarIdx = "";

    public String DriverHeadPosX = "";

    public String DriverHeadPosY = "";

    public String DriverHeadPosZ = "";

    public String DriverCarIdleRPM = "";

    public String DriverCarRedLine = "";

    public String DriverCarFuelKgPerLtr = "";

    public String DriverCarFuelMaxLtr = "";

    public String DriverCarMaxFuelPct = "";

    public String DriverCarSLFirstRPM = "";

    public String DriverCarSLShiftRPM = "";

    public String DriverCarSLLastRPM = "";

    public String DriverCarSLBlinkRPM = "";

    public String DriverPitTrkPct = "";

    public String DriverCarEstLapTime = "";

    public String DriverSetupName = "";

    public String DriverSetupIsModified = "";

    public String DriverSetupLoadTypeName = "";

    public String DriverSetupPassedTech = "";
    public String DriverUserID = "";
    public String PaceCarIdx = "";
    public String DriverCarIsElectric = "";
    public String DriverCarEngCylinderCount = "";
    public String DriverCarGearNumForward = "";
    public String DriverCarGearNeutral = "";
    public String DriverCarGearReverse = "";
    public String DriverCarVersion = "";
    public String DriverIncidentCount = "";

    public List<DriverInfoYaml> Drivers;


}
