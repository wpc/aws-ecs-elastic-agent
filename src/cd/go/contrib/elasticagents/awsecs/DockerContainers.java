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

import cd.go.contrib.elasticagents.Agents;
import cd.go.contrib.elasticagents.awsecs.requests.CreateAgentRequest;

import java.util.concurrent.ConcurrentHashMap;

import static cd.go.contrib.elasticagents.awsecs.AwsEcsPlugin.LOG;

public class DockerContainers extends ConcurrentHashMap<String, DockerContainer> {

    public DockerContainers() {
    }

    public DockerContainer create(CreateAgentRequest request, PluginSettings settings) throws Exception {
        DockerContainer container = DockerContainer.create(request, settings);
        this.put(container);
        return container;
    }

    public void refresh(String containerId, PluginSettings settings) throws Exception {
        if (!containsKey(containerId)) {
            this.put(DockerContainer.find(containerId));
        }
    }


    public void terminate(String containerId, PluginSettings settings) throws Exception {
        DockerContainer dockerContainer = this.get(containerId);
        if (dockerContainer != null) {
            dockerContainer.terminate();
        } else {
            AwsEcsPlugin.LOG.warn("Requested to terminate an instance that does not exist " + containerId);
        }

        this.remove(containerId);
    }

    public void terminateUnregisteredInstances(PluginSettings settings, Agents agents) throws Exception {
        DockerContainers toTerminate = unregisteredAfterTimeout(settings, agents);

        if (toTerminate.isEmpty()) {
            return;
        }

        LOG.warn("Terminating instances that did not register " + toTerminate.keySet() + ".");

        for (DockerContainer container : toTerminate.values()) {
            terminate(container.id(), settings);
        }
    }

    private DockerContainers unregisteredAfterTimeout(PluginSettings settings, Agents agents) {
        return null;
    }


    private void put(DockerContainer container) {
        this.put(container.id(), container);
    }


}
