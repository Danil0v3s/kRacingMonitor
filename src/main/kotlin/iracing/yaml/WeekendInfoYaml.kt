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
data class WeekendInfoYaml(
    var TrackName: String = "",
    var TrackID: String = "",
    var TrackLength: String = "",
    var TrackDisplayName: String = "",
    var TrackDisplayShortName: String = "",
    var TrackConfigName: String = "",
    var TrackCity: String = "",
    var TrackCountry: String = "",
    var TrackAltitude: String = "",
    var TrackLatitude: String = "",
    var TrackLongitude: String = "",
    var TrackNorthOffset: String = "",
    var TrackNumTurns: String = "",
    var TrackPitSpeedLimit: String = "",
    var TrackType: String = "",
    var TrackWeatherType: String = "",
    var TrackSkies: String = "",
    var TrackSurfaceTemp: String = "",
    var TrackAirTemp: String = "",
    var TrackAirPressure: String = "",
    var TrackWindVel: String = "",
    var TrackWindDir: String = "",
    var TrackRelativeHumidity: String = "",
    var TrackFogLevel: String = "",
    var TrackCleanup: String = "",
    var TrackDynamicTrack: String = "",
    var SeriesID: String = "",
    var SeasonID: String = "",
    var SessionID: String = "",
    var SubSessionID: String = "",
    var LeagueID: String = "",
    var Official: String = "",
    var RaceWeek: String = "",
    var EventType: String = "",
    var Category: String = "",
    var SimMode: String = "",
    var TeamRacing: String = "",
    var MinDrivers: String = "",
    var MaxDrivers: String = "",
    var DCRuleSet: String = "",
    var QualifierMustStartRace: String = "",
    var NumCarClasses: String = "",
    var NumCarTypes: String = "",
    var TrackLengthOfficial: String = "",
    var TrackDirection: String = "",
    var TrackVersion: String = "",
    var HeatRacing: String = "",
    var BuildType: String = "",
    var BuildTarget: String = "",
    var BuildVersion: String = "",
    var WeekendOptions: WeekendOptionsYaml? = null,
    var TelemetryOptions: TelemetryOptionsYaml? = null,
)
