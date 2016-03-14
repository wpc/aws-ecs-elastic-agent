/*
 * Copyright 2016 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.contrib.elasticagents.awsecs;

import cd.go.contrib.elasticagents.awsecs.requests.CreateAgentRequest;
import org.joda.time.DateTime;

public class DockerContainer {
    private String id;
    private DateTime createdAt;

    public String id() {
        return id;
    }

    public static DockerContainer find(String containerId) {
        return null;
    }

    public void terminate() {
    }

    public static DockerContainer create(CreateAgentRequest request, PluginSettings settings) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DockerContainer that = (DockerContainer) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
