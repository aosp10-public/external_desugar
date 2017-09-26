// Copyright 2017 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.android.desugar.testdata.java8;

/**
 * The test classes for desugaring default methods. Desugar is not expected to generate companion
 * classes for interfaces without default methods. The bridge methods are automatically generated by
 * javac and put in the implementing classes.
 *
 * <p>NOTE: There should be NO companion class generated for this class.
 */
public interface Java7InterfaceWithBridges<T> {
  T add(T value);

  /** Concretize T to {@link Number)} */
  interface LevelOne<T extends Number> extends Java7InterfaceWithBridges<T> {
    @Override
    T add(T value);
  }

  /** Concretize to {@link Integer} */
  interface LevelTwo extends LevelOne<Integer> {
    @Override
    Integer add(Integer value);
  }

  /** Empty abstract class. This class should have no bridge methods */
  abstract static class AbstractClassOne implements LevelTwo {}

  /** Implementing class. */
  static class ClassAddOne extends AbstractClassOne {
    @Override
    public Integer add(Integer value) {
      return value + 1;
    }
  }

  /** Implementing class. */
  static class ClassAddTwo extends AbstractClassOne implements LevelTwo {
    @Override
    public Integer add(Integer value) {
      return value + 2;
    }
  }
}