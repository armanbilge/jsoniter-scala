package com.github.plokhotnyuk.jsoniter_scala.benchmark

class ArrayOfUUIDsWritingSpec extends BenchmarkSpecBase {
  def benchmark: ArrayOfUUIDsWriting = new ArrayOfUUIDsWriting {
    setup()
  }

  "ArrayOfUUIDsWriting" should {
    "write properly" in {
      val b = benchmark
      toString(b.borer()) shouldBe b.jsonString
      toString(b.jsoniterScala()) shouldBe b.jsonString
      toString(b.preallocatedBuf, 64, b.jsoniterScalaPrealloc()) shouldBe b.jsonString
      toString(b.smithy4sJson()) shouldBe b.jsonString
    }
  }
}