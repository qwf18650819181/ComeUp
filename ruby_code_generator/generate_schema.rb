require 'graphql_java_gen'
require 'graphql_schema'
require 'json'
require 'fileutils'

# 确保输出目录存在
output_dir = "#{Dir.pwd}/../src/main/java/com/comeup/ruby/gen/"
FileUtils.mkdir_p(output_dir) unless File.directory?(output_dir)

# 读取并解析 GraphQL 模式
introspection_result = File.read("graphql_schema.json")
schema = GraphQLSchema.new(JSON.parse(introspection_result))

# 生成 Java 代码并保存到指定文件
GraphQLJavaGen.new(schema,
  package_name: "com.comeup.ruby.gen",
  nest_under: 'ExampleSchema',
  version: '2020-01',
  custom_scalars: [
    GraphQLJavaGen::Scalar.new(
      type_name: 'Decimal',
      java_type: 'BigDecimal',
      deserialize_expr: ->(expr) { "new BigDecimal(jsonAsString(#{expr}, key))" },
      imports: ['java.math.BigDecimal'],
    ),
  ]
).save("#{output_dir}/GeneratedCode.java")
