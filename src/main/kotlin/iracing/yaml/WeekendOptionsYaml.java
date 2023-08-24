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
public class WeekendOptionsYaml {


    public String NumStarters = "";

    public String StartingGrid = "";

    public String QualifyScoring = "";

    public String CourseCautions = "";

    public String StandingStart = "";

    public String Restarts = "";

    public String WeatherType = "";

    public String Skies = "";

    public String WindDirection = "";

    public String WindSpeed = "";

    public String WeatherTemp = "";

    public String RelativeHumidity = "";

    public String FogLevel = "";

    public String Unofficial = "";

    public String CommercialMode = "";

    public String NightMode = "";

    public String IsFixedSetup = "";

    public String StrictLapsChecking = "";

    public String HasOpenRegistration = "";
    public String ShortParadeLap = "";
    public String TimeOfDay = "";
    public String Date = "";
    public String EarthRotationSpeedupFactor = "";
    public String HardcoreLevel = "";
    public String NumJokerLaps = "";
    public String IncidentLimit = "";
    public String FastRepairsLimit = "";
    public String GreenWhiteCheckeredLimit = "";

}
