using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Chrome.Cast.RNChromeCast
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNChromeCastModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNChromeCastModule"/>.
        /// </summary>
        internal RNChromeCastModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNChromeCast";
            }
        }
    }
}
