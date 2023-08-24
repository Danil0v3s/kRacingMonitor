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
data class DriverInfoYaml(
    var CarIdx: String = "",
    var UserName: String = "",
    var AbbrevName: String? = null,
    var Initials: String? = null,
    var UserID: String = "",
    var TeamID: String = "",
    var TeamName: String = "",
    var CarNumber: String = "",
    var CarNumberRaw: String = "",
    var CarPath: String = "",
    var CarClassID: String = "",
    var CarID: String = "",
    var CarIsPaceCar: String = "",
    var CarIsAI: String = "",
    var CarScreenName: String = "",
    var CarScreenNameShort: String = "",
    var CarClassShortName: String? = null,
    var CarClassRelSpeed: String = "",
    var CarClassLicenseLevel: String = "",
    var CarClassMaxFuelPct: String = "",
    var CarClassWeightPenalty: String = "",
    var CarClassColor: String = "",
    var IRating: String = "",
    var LicLevel: String = "",
    var LicSubLevel: String = "",
    var LicString: String = "",
    var LicColor: String = "",
    var IsSpectator: String = "",
    var CarDesignStr: String = "",
    var HelmetDesignStr: String = "",
    var SuitDesignStr: String = "",
    var CarNumberDesignStr: String = "",
    var CarSponsor_1: String = "",
    var CarSponsor_2: String = "",
    var ClubName: String = "",
    var DivisionName: String = "",
    var CarIsElectric: String = "",
    var CarEngCylinderCount: String = "",
    var CarGearNumForward: String = "",
    var CarGearNeutral: String = "",
    var CarGearReverse: String = "",
    var CarVersion: String = "",
    var IncidentCount: String = "",
    var CarClassPowerAdjust: String = "",
    var CarClassDryTireSetLimit: String = "",
    var CarClassEstLapTime: String = "",
    var BodyType: String = "",
    var FaceType: String = "",
    var HelmetType: String = "",
    var CurDriverIncidentCount: String = "",
    var TeamIncidentCount: String = "",
)
