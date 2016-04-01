/**
 * Copyright 2016 StreamSets Inc.
 *
 * Licensed under the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streamsets.datacollector.event.binding;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.streamsets.datacollector.config.dto.PipelineConfigAndRules;
import com.streamsets.datacollector.event.dto.PipelineBaseEvent;
import com.streamsets.datacollector.event.dto.PipelineSaveEvent;
import com.streamsets.datacollector.event.json.PipelineBaseEventJson;
import com.streamsets.datacollector.event.json.PipelineSaveEventJson;


public class TestDtoJsonMapper {

  @Test
  public void testPipelineBaseEventJson() throws Exception {
    PipelineBaseEvent pde = new PipelineBaseEvent("name1", "rev1", "user1");
    PipelineBaseEventJson pdeJson = MessagingDtoJsonMapper.INSTANCE.toPipelineBaseEventJson(pde);
    assertEquals("name1", pdeJson.getName());
    assertEquals("rev1", pdeJson.getRev());
    assertEquals("user1", pdeJson.getUser());
    PipelineBaseEvent pdeDto = MessagingDtoJsonMapper.INSTANCE.asPipelineBaseEventDto(pdeJson);
    assertEquals("name1", pdeDto.getName());
    assertEquals("rev1", pdeDto.getRev());
    assertEquals("user1", pdeDto.getUser());
  }

  @Test
  public void testPipelineSaveEventJson() throws Exception {
    PipelineConfigAndRules pipelineConfigAndRules = new PipelineConfigAndRules("config", "rules");
    PipelineSaveEvent pipelineSaveEvent =
     new PipelineSaveEvent("name1", "rev1", "user1", "desc", pipelineConfigAndRules);
    PipelineSaveEventJson pseJson = MessagingDtoJsonMapper.INSTANCE.toPipelineSaveEventJson(pipelineSaveEvent);
    assertEquals("config", pseJson.getPipelineConfigurationAndRules().getPipelineConfig());
    assertEquals("rules", pseJson.getPipelineConfigurationAndRules().getPipelineRules());
    assertEquals("name1", pseJson.getName());
    assertEquals("rev1", pseJson.getRev());
    assertEquals("user1", pseJson.getUser());
    assertEquals("desc", pseJson.getDescription());

    pipelineSaveEvent = MessagingDtoJsonMapper.INSTANCE.asPipelineSaveEventDto(pseJson);
    assertEquals("name1", pipelineSaveEvent.getName());
    assertEquals("rev1", pipelineSaveEvent.getRev());
    assertEquals("user1", pipelineSaveEvent.getUser());
    assertEquals("config", pipelineSaveEvent.getPipelineConfigurationAndRules().getPipelineConfig());
    assertEquals("rules", pipelineSaveEvent.getPipelineConfigurationAndRules().getPipelineRules());

  }
}