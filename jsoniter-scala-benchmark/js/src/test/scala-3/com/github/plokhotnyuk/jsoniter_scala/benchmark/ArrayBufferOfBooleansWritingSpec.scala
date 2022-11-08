package com.github.plokhotnyuk.jsoniter_scala.benchmark

class ArrayBufferOfBooleansWritingSpec extends BenchmarkSpecBase {
  def benchmark: ArrayBufferOfBooleansWriting = new ArrayBufferOfBooleansWriting {
    setup()
  }

  "ArrayBufferOfBooleansWriting" should {
    "write properly" in {
      val b = benchmark
      toString(b.borer()) shouldBe b.jsonString
      toString(b.jsoniterScala()) shouldBe b.jsonString
      toString(b.preallocatedBuf, 64, b.jsoniterScalaPrealloc()) shouldBe b.jsonString
    }
  }
}