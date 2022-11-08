package com.github.plokhotnyuk.jsoniter_scala.core

import scala.scalanative.unsafe._

@extern
object NativeMath {
  
  @name("jsoniter_scala_multiply_high")
  def multiplyHigh(x: CLong, y: CLong): CLong = extern

}
