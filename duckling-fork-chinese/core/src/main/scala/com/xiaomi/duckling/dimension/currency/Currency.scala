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

package com.xiaomi.duckling.dimension.currency

import com.xiaomi.duckling.Types
import com.xiaomi.duckling.Types.Resolvable
import com.xiaomi.duckling.dimension.Dimension
import com.xiaomi.duckling.dimension.implicits._
import com.xiaomi.duckling.dimension.numeral.Numeral
import com.xiaomi.duckling.dimension.quantity.QuantityValue

case object Currency extends Dimension with Rules with Examples {
  override val name: String = "Currency"

  override val dimDependents: List[Dimension] = List(Numeral)
}

case class CurrencyData(v: Double,
                        scalar: Double,
                        unit: String,
                        abbr: Boolean = false,
                        compose: Boolean = false,
                        end: Boolean = false,
                        code: Option[String] = None)
  extends Resolvable {
  override def resolve(context: Types.Context,
                       options: Types.Options): Option[(Types.ResolvedValue, Boolean)] = {
    val dim = code.getOrElse("*")
    (QuantityValue(v * scalar, "元", s"货币:$dim"), false)
  }
}
