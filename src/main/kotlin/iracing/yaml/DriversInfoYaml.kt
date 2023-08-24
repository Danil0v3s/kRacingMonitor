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
package iracing.yaml

import kotlinx.serialization.Serializable

@Serializable
data class DriversInfoYaml(
    var DriverCarIdx: String = "",
    var DriverHeadPosX: String = "",
    var DriverHeadPosY: String = "",
    var DriverHeadPosZ: String = "",
    var DriverCarIdleRPM: String = "",
    var DriverCarRedLine: String = "",
    var DriverCarFuelKgPerLtr: String = "",
    var DriverCarFuelMaxLtr: String = "",
    var DriverCarMaxFuelPct: String = "",
    var DriverCarSLFirstRPM: String = "",
    var DriverCarSLShiftRPM: String = "",
    var DriverCarSLLastRPM: String = "",
    var DriverCarSLBlinkRPM: String = "",
    var DriverPitTrkPct: String = "",
    var DriverCarEstLapTime: String = "",
    var DriverSetupName: String = "",
    var DriverSetupIsModified: String = "",
    var DriverSetupLoadTypeName: String = "",
    var DriverSetupPassedTech: String = "",
    var DriverUserID: String = "",
    var PaceCarIdx: String = "",
    var DriverCarIsElectric: String = "",
    var DriverCarEngCylinderCount: String = "",
    var DriverCarGearNumForward: String = "",
    var DriverCarGearNeutral: String = "",
    var DriverCarGearReverse: String = "",
    var DriverCarVersion: String = "",
    var DriverIncidentCount: String = "",
    var Drivers: List<DriverInfoYaml>? = null
)
