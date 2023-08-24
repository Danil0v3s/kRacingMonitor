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


@Serializable
public class DriverInfoYaml {


    public String CarIdx = "";

    public String UserName = "";

    public String AbbrevName = "";

    public String Initials = "";

    public String UserID = "";

    public String TeamID = "";

    public String TeamName = "";

    public String CarNumber = "";

    public String CarNumberRaw = "";

    public String CarPath = "";

    public String CarClassID = "";

    public String CarID = "";

    public String CarIsPaceCar = "";

    public String CarIsAI = "";

    public String CarScreenName = "";

    public String CarScreenNameShort = "";

    public String CarClassShortName = "";

    public String CarClassRelSpeed = "";

    public String CarClassLicenseLevel = "";

    public String CarClassMaxFuelPct = "";

    public String CarClassWeightPenalty = "";

    public String CarClassColor = "";

    public String IRating = "";

    public String LicLevel = "";

    public String LicSubLevel = "";

    public String LicString = "";

    public String LicColor = "";

    public String IsSpectator = "";

    public String CarDesignStr = "";

    public String HelmetDesignStr = "";

    public String SuitDesignStr = "";

    public String CarNumberDesignStr = "";

    public String CarSponsor_1 = "";

    public String CarSponsor_2 = "";

    public String ClubName = "";

    public String DivisionName = "";
    public String CarIsElectric = "";
    public String CarEngCylinderCount = "";
    public String CarGearNumForward = "";
    public String CarGearNeutral = "";
    public String CarGearReverse = "";
    public String CarVersion = "";
    public String IncidentCount = "";
    public String CarClassPowerAdjust = "";
    public String CarClassDryTireSetLimit = "";
    public String CarClassEstLapTime = "";
    public String BodyType = "";
    public String FaceType = "";
    public String HelmetType = "";
    public String CurDriverIncidentCount = "";
    public String TeamIncidentCount = "";

}
