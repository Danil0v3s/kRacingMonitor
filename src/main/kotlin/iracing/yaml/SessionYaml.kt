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
data class SessionYaml(
    var SessionNum: String = "",
    var SessionLaps: String = "",
    var SessionTime: String = "",
    var SessionNumLapsToAvg: String = "",
    var SessionType: String = "",
    var SessionTrackRubberState: String = "",
    var ResultsPositions: List<ResultsPositionsYaml>? = null,
    var ResultsFastestLap: List<ResultsFastestLapYaml>? = null,
    var ResultsAverageLapTime: String = "",
    var ResultsNumCautionFlags: String = "",
    var ResultsNumCautionLaps: String = "",
    var ResultsNumLeadChanges: String = "",
    var ResultsLapsComplete: String = "",
    var ResultsOfficial: String = "",
    var SessionName: String = "",
    var SessionSubType: String? = null,
    var SessionSkipped: String = "",
    var SessionRunGroupsUsed: String = "",
    var SessionEnforceTireCompoundChange: String = "",
)
