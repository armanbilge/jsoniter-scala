package com.github.plokhotnyuk.jsoniter_scala.benchmark

import org.openjdk.jmh.annotations.Benchmark

class ListOfBooleansReading extends ListOfBooleansBenchmark {
  @Benchmark
  def borer(): List[Boolean] = {
    import io.bullet.borer.Json

    Json.decode(jsonBytes).to[List[Boolean]].value
  }

  @Benchmark
  def circe(): List[Boolean] = {
    import io.circe.jawn._

    decodeByteArray[List[Boolean]](jsonBytes).fold(throw _, identity)
  }

  @Benchmark
  def circeJsoniter(): List[Boolean] = {
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.CirceJsoniterCodecs._
    import com.github.plokhotnyuk.jsoniter_scala.core._
    import io.circe.Decoder

    Decoder[List[Boolean]].decodeJson(readFromArray(jsonBytes)).fold(throw _, identity)
  }

  @Benchmark
  def jacksonScala(): List[Boolean] = {
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.JacksonSerDesers._

    jacksonMapper.readValue[List[Boolean]](jsonBytes)
  }

  @Benchmark
  def json4sJackson(): List[Boolean] = {
    import org.json4s._
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.Json4sJacksonMappers._
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.CommonJson4sFormats._

    mapper.readValue[JValue](jsonBytes, jValueType).extract[List[Boolean]]
  }

  @Benchmark
  def json4sNative(): List[Boolean] = {
    import org.json4s._
    import org.json4s.native.JsonMethods._
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.CommonJson4sFormats._
    import java.nio.charset.StandardCharsets.UTF_8

    parse(new String(jsonBytes, UTF_8)).extract[List[Boolean]]
  }

  @Benchmark
  def jsoniterScala(): List[Boolean] = {
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.JsoniterScalaCodecs._
    import com.github.plokhotnyuk.jsoniter_scala.core._

    readFromArray[List[Boolean]](jsonBytes)
  }

  @Benchmark
  def smithy4sJson(): List[Boolean] = {
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.Smithy4sJCodecs._
    import com.github.plokhotnyuk.jsoniter_scala.core._

    readFromArray[List[Boolean]](jsonBytes)
  }

  @Benchmark
  def sprayJson(): List[Boolean] = {
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.SprayFormats._
    import spray.json._

    JsonParser(jsonBytes).convertTo[List[Boolean]]
  }

  @Benchmark
  def uPickle(): List[Boolean] = {
    import upickle.default._

    read[List[Boolean]](jsonBytes)
  }

  @Benchmark
  def weePickle(): List[Boolean] = {
    import com.rallyhealth.weejson.v1.jackson.FromJson
    import com.rallyhealth.weepickle.v1.WeePickle.ToScala

    FromJson(jsonBytes).transform(ToScala[List[Boolean]])
  }

  @Benchmark
  def zioJson(): List[Boolean] = {
    import zio.json.DecoderOps
    import java.nio.charset.StandardCharsets.UTF_8

    new String(jsonBytes, UTF_8).fromJson[List[Boolean]].fold(sys.error, identity)
  }
}