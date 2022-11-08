package com.github.plokhotnyuk.jsoniter_scala.benchmark

class ArrayOfFloatsWritingSpec extends BenchmarkSpecBase {
  def benchmark: ArrayOfFloatsWriting = new ArrayOfFloatsWriting {
    setup()
  }

  "ArrayOfFloatsWriting" should {
    "write properly" in {
      val b = benchmark
      check(toString(b.borer()), b.jsonString)
      check(toString(b.jsoniterScala()), b.jsonString)
      check(toString(b.preallocatedBuf, 64, b.jsoniterScalaPrealloc()), b.jsonString)
      check(toString(b.smithy4sJson()), b.jsonString)
    }
  }

  private[this] def check(actual: String, expected: String): Unit =
    actual.substring(1, actual.length - 1).split(',')
      .zip(expected.substring(1, expected.length - 1).split(',')).foreach { case (a, e) =>
      require(a.toFloat == e.toFloat, s"expected: $e, but got: $a when parsed back to float")
    }
}