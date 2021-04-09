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

package duckling.dimension.duplicate

import duckling.dimension.DimRules
import duckling.Types._
import duckling.dimension.implicits._
import duckling.dimension.matcher.Prods.regexMatch

trait Rules extends DimRules {
  val rule = Rule(
    name = "duplicates",
    pattern = List("((.+?)\\2+)".regex),
    prod = regexMatch {
      case s :: _ :: w :: _ =>
        token(w, s.length / w.length)
    }
  )
}