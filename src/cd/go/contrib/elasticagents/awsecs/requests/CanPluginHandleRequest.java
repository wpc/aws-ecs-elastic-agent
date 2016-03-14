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

package cd.go.contrib.elasticagents.awsecs.requests;

import cd.go.contrib.elasticagents.awsecs.DockerContainers;
import cd.go.contrib.elasticagents.awsecs.PluginSettings;
import cd.go.contrib.elasticagents.awsecs.executors.CanPluginHandleRequestExecutor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;

public class CanPluginHandleRequest {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    private Collection<String> resources;
    private String environment;

    public CanPluginHandleRequest() {

    }

    public CanPluginHandleRequest(Collection<String> resources, String environment) {
        this.resources = resources;
        this.environment = environment;
    }

    public Collection<String> resources() {
        return resources;
    }

    public String environment() {
        return environment;
    }

    public static CanPluginHandleRequest fromJSON(String json) {
        return GSON.fromJson(json, CanPluginHandleRequest.class);
    }

    public CanPluginHandleRequestExecutor executor(DockerContainers containers, PluginSettings settings) {
        return new CanPluginHandleRequestExecutor(this, containers, settings);
    }
}
