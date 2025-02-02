/*
 * Copyright (c) 2020, Xiaomi and/or its affiliates. All rights reserved.
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

package com.xiaomi.duckling.dimension

import com.typesafe.scalalogging.LazyLogging

import com.xiaomi.duckling.ranking.Testing.Corpus
import com.xiaomi.duckling.Types.{conf, Rule}

object CorpusSets extends LazyLogging {

  type CorpusSet = (Dimension, Corpus, List[Rule])

  val dims = {
    val className = conf.getString("dims")
    logger.info(s"pick up dims of: $className")
    Class.forName(className).getConstructor().newInstance().asInstanceOf[Dimensions].dims
  }

  val namedDimensions: Map[String, Dimension] = Dimension.dimDependents(dims)
    .map(dim => (dim.name.toLowerCase(), dim))
    .toMap
}
