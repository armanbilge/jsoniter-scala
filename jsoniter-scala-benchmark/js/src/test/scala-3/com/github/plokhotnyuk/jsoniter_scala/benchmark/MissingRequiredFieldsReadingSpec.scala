package com.github.plokhotnyuk.jsoniter_scala.benchmark

import java.nio.charset.StandardCharsets.UTF_8

class MissingRequiredFieldsReadingSpec extends BenchmarkSpecBase {
  def benchmark: MissingRequiredFieldsReading = new MissingRequiredFieldsReading {
    setup()
  }

  "MissingRequiredFieldsReading" should {
    "return some parsing error" in {
      val b = benchmark
      b.borer() shouldBe
        "Cannot decode `MissingRequiredFields` instance due to missing map keys \"s\" and \"i\" (input position 1)"
      b.jsoniterScala() shouldBe
        """missing required field "s", offset: 0x00000001, buf:
          |+----------+-------------------------------------------------+------------------+
          ||          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f | 0123456789abcdef |
          |+----------+-------------------------------------------------+------------------+
          || 00000000 | 7b 7d                                           | {}               |
          |+----------+-------------------------------------------------+------------------+""".stripMargin
      b.jsoniterScalaWithoutDump() shouldBe """missing required field "s", offset: 0x00000001"""
      b.jsoniterScalaWithStacktrace() shouldBe
        """missing required field "s", offset: 0x00000001, buf:
          |+----------+-------------------------------------------------+------------------+
          ||          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f | 0123456789abcdef |
          |+----------+-------------------------------------------------+------------------+
          || 00000000 | 7b 7d                                           | {}               |
          |+----------+-------------------------------------------------+------------------+""".stripMargin
      b.smithy4sJson() shouldBe "Missing required field (path: .s)"
    }
    "return toString value for valid input" in {
      val b = benchmark
      b.jsonBytes = """{"s":"VVV","i":1}""".getBytes(UTF_8)
      b.borer() shouldBe "MissingRequiredFields(VVV,1)"
      b.jsoniterScala() shouldBe "MissingRequiredFields(VVV,1)"
      b.jsoniterScalaWithoutDump() shouldBe "MissingRequiredFields(VVV,1)"
      b.jsoniterScalaWithStacktrace() shouldBe "MissingRequiredFields(VVV,1)"
      b.smithy4sJson() shouldBe "MissingRequiredFields(VVV,1)"
    }
  }
}