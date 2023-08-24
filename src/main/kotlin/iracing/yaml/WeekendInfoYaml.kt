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
public class WeekendInfoYaml {


    public String TrackName = "";

    public String TrackID = "";

    public String TrackLength = "";

    public String TrackDisplayName = "";

    public String TrackDisplayShortName = "";

    public String TrackConfigName = "";

    public String TrackCity = "";

    public String TrackCountry = "";

    public String TrackAltitude = "";

    public String TrackLatitude = "";

    public String TrackLongitude = "";

    public String TrackNorthOffset = "";

    public String TrackNumTurns = "";

    public String TrackPitSpeedLimit = "";

    public String TrackType = "";

    public String TrackWeatherType = "";

    public String TrackSkies = "";

    public String TrackSurfaceTemp = "";

    public String TrackAirTemp = "";

    public String TrackAirPressure = "";

    public String TrackWindVel = "";

    public String TrackWindDir = "";

    public String TrackRelativeHumidity = "";

    public String TrackFogLevel = "";

    public String TrackCleanup = "";

    public String TrackDynamicTrack = "";

    public String SeriesID = "";

    public String SeasonID = "";

    public String SessionID = "";

    public String SubSessionID = "";

    public String LeagueID = "";

    public String Official = "";

    public String RaceWeek = "";

    public String EventType = "";

    public String Category = "";

    public String SimMode = "";

    public String TeamRacing = "";

    public String MinDrivers = "";

    public String MaxDrivers = "";

    public String DCRuleSet = "";

    public String QualifierMustStartRace = "";

    public String NumCarClasses = "";

    public String NumCarTypes = "";

    public String TrackLengthOfficial = "";
    public String TrackDirection = "";
    public String TrackVersion = "";
    public String HeatRacing = "";
    public String BuildType = "";
    public String BuildTarget = "";
    public String BuildVersion = "";

    public WeekendOptionsYaml WeekendOptions;

    public TelemetryOptionsYaml TelemetryOptions;

}
